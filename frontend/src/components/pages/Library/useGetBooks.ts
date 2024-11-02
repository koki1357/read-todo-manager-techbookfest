// hooks/useBooks.ts
import { Book } from '@/common/types';
import { createHeader } from '@/common/util/api-util';
import { errorAtom } from '@/components/organisms/error-modal/ErrorModal';
import axios from 'axios';
import { useAtom } from 'jotai';
import { useEffect, useState } from 'react';

const useBooks = () => {
  const [books, setBooks] = useState<Book[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [, setErrorState] = useAtom(errorAtom);

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/v1/books/', {
          headers: createHeader(),
        });
        setBooks(response.data.data);
      } catch (error: any) {
        setErrorState({
          hasError: true,
          messages: [error.response.data.message],
        });
        setError(error);
        throw error;
      } finally {
        setLoading(false);
      }
    };

    fetchBooks();
  }, [setErrorState]);

  return { books, loading, error };
};

export default useBooks;
