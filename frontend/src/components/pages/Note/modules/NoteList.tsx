import React from 'react';
import styles from './NoteList.module.scss';

interface NoteListProps {
  notes: string[];
}

const NoteList: React.FC<NoteListProps> = ({ notes }) => (
  <ul className={styles.noteList}>
    {notes.map((note, index) => (
      <li key={index} className={styles.noteItem}>
        {note}
      </li>
    ))}
  </ul>
);

export default NoteList;
