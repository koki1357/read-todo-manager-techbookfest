// components/Bookshelf.tsx
import { Book } from '@/common/types';
import ApiHandlerContainer from '@/components/organisms/ApiHandlerContainer';
import Header from '@/components/organisms/Header';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './LibraryPage.module.scss';
import BookList from './modules/BookList';
import SearchBar from './modules/SearchBar';
import useGetBooks from './useGetBooks';

const LibraryPage: React.FC = () => {
  const navigate = useNavigate();
  const handleRegisterBook = () => {
    navigate('/books/register');
  };
  const { books, loading, error } = useGetBooks();
  const [searchResults, setSearchResults] = useState<Book[] | null>(null);

  const handleSearchResults = (results: Book[]) => {
    setSearchResults(results);
  };

  return (
    <ApiHandlerContainer loading={loading} error={error}>
      <div className={styles.bookshelf}>
        <Header title="Bookshelf" hideBackIcon={true} />
        <SearchBar onSearchResults={handleSearchResults} />
        <h2 className={styles.subtitle}>Your Books</h2>
        <BookList books={searchResults ?? books} />
        <AddCircleIcon className={styles.plusButton} onClick={handleRegisterBook}></AddCircleIcon>
      </div>
    </ApiHandlerContainer>
  );
};

export default LibraryPage;
