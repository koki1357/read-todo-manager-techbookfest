// components/BookItem.tsx
import { Book } from '@/common/types';
import React from 'react';
import { useNavigate } from 'react-router-dom';
import NoImage from '../../../../assets/no-image.png';
import styles from './BookItem.module.scss';

const BookItem: React.FC<Book> = ({ id, title, image }) => {
  const navigate = useNavigate();
  const handleClick = () => {
    navigate(`/books/${id}`);
  };
  return (
    <div className={styles.bookItem} onClick={handleClick}>
      <img src={image ?? NoImage} alt={title} className={styles.image} />
      <p className={styles.title}>{title}</p>
    </div>
  );
};

export default BookItem;
