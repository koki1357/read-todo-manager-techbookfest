// frontend/src/hooks/useBookDetail.ts
import { Book } from '@/common/types';
import { createHeader } from '@/common/util/api-util';
import { errorAtom } from '@/components/organisms/error-modal/ErrorModal';
import axios from 'axios';
import { useAtom } from 'jotai';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

const useBookDetail = () => {
  const { id } = useParams<{ id: string }>();
  const [book, setBook] = useState<Book>();
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [, setErrorState] = useAtom(errorAtom);

  useEffect(() => {
    const fetchBookDetail = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/v1/books/`, {
          headers: createHeader(),
          params: {
            id,
          },
        });
        setBook(response.data.data[0]);
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

    fetchBookDetail();
  }, [id, setErrorState]);

  return { book, loading, error };
};

export default useBookDetail;
