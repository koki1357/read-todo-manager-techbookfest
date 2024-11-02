import { createHeader } from '@/common/util/api-util';
import axios from 'axios';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

export type Note = {
  id: number;
  bookId: number;
  userId: string;
  content: string;
  createdAt: string;
  updatedAt: string;
};

const useGetNotes = (updatedNoteId: number | null, deletedNoteId: number | null) => {
  const { id } = useParams<{ id: string }>();
  const [notes, setNotes] = useState<Note[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchNotes = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/v1/memo/`, {
          headers: createHeader(),
          params: { bookId: id },
        });
        console.log(response.data.data);
        setNotes(response.data.data);
      } catch (error) {
        setError('Error fetching notes');
      } finally {
        setLoading(false);
      }
    };

    fetchNotes();
  }, [id, deletedNoteId, updatedNoteId]);

  return { notes, loading, error };
};

export default useGetNotes;
