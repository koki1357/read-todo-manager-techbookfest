import React from 'react';
import styles from './BookInfo.module.scss';

interface BookInfoProps {
  title: string;
  author: string;
  image: string;
}

const BookInfo: React.FC<BookInfoProps> = ({ title, author, image }) => (
  <div className={styles.bookInfo}>
    <img src={image} alt={title} className={styles.cover} />
    <div className={styles.details}>
      <h2 className={styles.title}>{title}</h2>
      <p className={styles.author}>{author}</p>
    </div>
  </div>
);

export default BookInfo;
