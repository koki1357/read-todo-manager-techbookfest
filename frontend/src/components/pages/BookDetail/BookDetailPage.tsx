// BookDetailsPage.tsx
import { createHeader } from '@/common/util/api-util';
import ApiHandlerContainer from '@/components/organisms/ApiHandlerContainer';
import Header from '@/components/organisms/Header';
import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './BookDetailPage.module.scss';
import BookDetail from './modules/BookDetail';
import NoteList from './modules/NoteList';
import useGetBookDetail from './useGetBookDetail';
import useGetNotes from './useGetNotes';

const BookDetailPage: React.FC = () => {
  const navigate = useNavigate();

  const [updatedNoteId, setUpdatedNoteId] = useState<number | null>(null);
  const [deletedNoteId, setDeletedNoteId] = useState<number | null>(null);

  const { book, loading: bookLoading, error: bookError } = useGetBookDetail();

  const {
    notes,
    loading: notesLoading,
    error: notesError,
  } = useGetNotes(updatedNoteId, deletedNoteId);

  const handleUpdateNote = async (index: number, newNote: string) => {
    try {
      const note = notes[index];
      const response = await axios.put(
        `http://localhost:8080/api/v1/memo/`,
        {
          id: note.id,
          content: newNote,
        },
        {
          headers: createHeader(),
        }
      );
      setUpdatedNoteId(response.data.data);
      // const updatedNotes = notes.map((note, i) => (i === index ? newNote : note));
      // setNotes(updatedNotes);
    } catch (error) {
      console.error('Failed to update note:', error);
    }
  };

  const handleDeleteNote = async (id: number) => {
    try {
      const response = await axios.delete(`http://localhost:8080/api/v1/memo/${id}`, {
        headers: createHeader(),
      });
      setDeletedNoteId(response.data.data);
    } catch (error) {
      console.error('Failed to delete note:', error);
    }
  };

  const handleGoBack = () => {
    navigate('/books');
  };

  return (
    <ApiHandlerContainer loading={bookLoading || notesLoading} error={bookError || notesError}>
      <div className={styles.container}>
        <Header handleBackIconClick={handleGoBack} title={book ? book.title : 'Loading...'} />
        <main className={styles.main}>
          {book ? (
            <>
              {book.image && <img src={book.image} alt={book.title} className={styles.cover} />}
              <BookDetail book={book} notesLength={notes.length} />
              <NoteList
                notes={notes}
                onUpdateNote={handleUpdateNote}
                onDeleteNote={handleDeleteNote}
              />
            </>
          ) : (
            <p>データがありません</p>
          )}
        </main>
      </div>
    </ApiHandlerContainer>
  );
};

export default BookDetailPage;
