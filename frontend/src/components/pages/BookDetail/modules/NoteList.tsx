import React, { useState } from 'react';
import { Note } from '../useGetNotes';
import styles from './NoteList.module.scss';

interface NoteListProps {
  notes: Note[];
  onUpdateNote: (index: number, newNote: string) => void;
  onDeleteNote: (index: number) => void;
}

const NoteList: React.FC<NoteListProps> = ({ notes, onUpdateNote, onDeleteNote }) => {
  const [editIndex, setEditIndex] = useState<number | null>(null);
  const [editText, setEditText] = useState<string>('');

  const handleEdit = (index: number, note: string) => {
    setEditIndex(index);
    setEditText(note);
  };

  const handleUpdate = (index: number) => {
    onUpdateNote(index, editText);
    setEditIndex(null);
    setEditText('');
  };

  return (
    <ul className={styles.noteList}>
      {notes &&
        notes.map((note, index) => (
          <li key={index} className={styles.noteItem}>
            {editIndex === index ? (
              <>
                <textarea
                  value={editText}
                  onChange={(e) => setEditText(e.target.value)}
                  className={styles.editInput}
                />
                <button onClick={() => handleUpdate(index)} className={styles.updateButton}>
                  Update
                </button>
                <button onClick={() => setEditIndex(null)} className={styles.cancelButton}>
                  Cancel
                </button>
              </>
            ) : (
              <>
                <span>{note.content}</span>
                <button
                  onClick={() => handleEdit(index, note.content)}
                  className={styles.editButton}
                >
                  Update
                </button>
                <button onClick={() => onDeleteNote(note.id)} className={styles.deleteButton}>
                  Delete
                </button>
              </>
            )}
          </li>
        ))}
    </ul>
  );
};

export default NoteList;
