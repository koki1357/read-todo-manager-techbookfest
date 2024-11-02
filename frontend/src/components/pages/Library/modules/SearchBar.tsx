// components/SearchBar.tsx
import { Book } from '@/common/types';
import { createHeader } from '@/common/util/api-util';
import axios from 'axios';
import React, { useState } from 'react';
import styles from './SearchBar.module.scss';

interface SearchBarProps {
  onSearchResults: (results: Book[]) => void;
}

const SearchBar: React.FC<SearchBarProps> = ({ onSearchResults }) => {
  const [query, setQuery] = useState<string>('');

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setQuery(event.target.value);
  };

  const handleSearch = async () => {
    console.log('searching for:', query);
    try {
      const response = await axios.get(`http://localhost:8080/api/v1/search/books/`, {
        headers: createHeader(),
        params: {
          title: query,
        },
      });
      const data = await response.data.data;
      console.log(data); // ここでデータを処理します
      onSearchResults(data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };
  return (
    <div className={styles.searchBar}>
      <input
        type="text"
        placeholder="Search..."
        className={styles.input}
        value={query}
        onChange={handleInputChange}
      />
      <button className={styles.searchButton} onClick={handleSearch}>
        🔍
      </button>
    </div>
  );
};

export default SearchBar;
