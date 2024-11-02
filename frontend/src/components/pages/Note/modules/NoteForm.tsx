import React, { useState } from 'react';
import styles from './NoteForm.module.scss';
import MainButton from '@/components/atoms/MainButton';
import { useRegisterNote } from './useRegisterNote';
import { useNavigate } from 'react-router-dom';

interface NoteInputProps {
  bookId: number;
}

const NoteInput: React.FC<NoteInputProps> = ({ bookId }) => {
  const navigate = useNavigate();
  const [note, setNote] = useState('');

  const { registerNote } = useRegisterNote();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (note.trim()) {
      try {
        const payload = {
          bookId: bookId,
          userId: '1',
          content: note,
        };
        await registerNote(payload);
        setNote('');
        navigate('/books/' + bookId);
      } catch (error) {
        console.error('Error registering note:', error);
      }
    }
  };

  return (
    <form onSubmit={handleSubmit} className={styles.noteInput}>
      <textarea
        value={note}
        onChange={(e) => setNote(e.target.value)}
        placeholder="Add a note"
        className={styles.input}
      />
      <MainButton type="submit" onClick={() => console.log('Save Note')}>
        Save Note
      </MainButton>
    </form>
  );
};

export default NoteInput;
