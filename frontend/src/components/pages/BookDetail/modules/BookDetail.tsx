// BookDetails.tsx
import React from 'react';
import { Person, CalendarToday, MenuBook, Notes } from '@mui/icons-material';
import styles from './BookDetail.module.scss';
import { useNavigate, useParams } from 'react-router-dom';
import { Book } from '@/common/types';

interface BookDetailProps {
  book: Book;
  notesLength: number;
}

const BookDetail: React.FC<BookDetailProps> = ({ book, notesLength }) => {
  const navigate = useNavigate();

  const { id } = useParams<{ id: string }>(); // パスパラメータを取得

  const handleAddNoteClick = () => {
    navigate(`/books/${id}/note`);
  };
  return (
    <div className={styles.details}>
      <h2 className={styles.sectionTitle}>Details</h2>
      <ul className={styles.detailsList}>
        <li className={styles.detailItem}>
          <Person />
          <span className={styles.detailLabel}>Author</span>
          <span className={styles.detailValue}>{book.author}</span>
        </li>
        <li className={styles.detailItem}>
          <CalendarToday />
          <span className={styles.detailLabel}>Start Date</span>
          <span className={styles.detailValue}>{book.startDate}</span>
        </li>
        <li className={styles.detailItem}>
          <CalendarToday />
          <span className={styles.detailLabel}>Finish Date</span>
          <span className={styles.detailValue}>{book.finishDate}</span>
        </li>
        <li className={styles.detailItem}>
          <MenuBook />
          <span className={styles.detailLabel}>Reading schedule</span>
          {book.daysPerWeek && (
            <span className={styles.detailValue}>{book.daysPerWeek} days per week</span>
          )}
        </li>
        <li className={styles.detailItem}>
          <Notes />
          <span className={styles.detailLabel}>Notes</span>
          <span className={styles.detailValue}>{notesLength} notes</span>
          <button className={styles.addNoteButton} onClick={handleAddNoteClick}>
            Add Note
          </button>
        </li>
      </ul>
    </div>
  );
};

export default BookDetail;
