// components/BookList.tsx
import { Book } from '@/common/types';
import React from 'react';
import BookItem from './BookItem';
import styles from './BookList.module.scss';

interface BookListProps {
  books: Book[];
}

const BookList: React.FC<BookListProps> = ({ books }) => {
  return (
    <div className={styles.bookList}>
      {books && books.length !== 0 && books.map((book) => <BookItem key={book.id} {...book} />)}
    </div>
  );
};

export default BookList;
