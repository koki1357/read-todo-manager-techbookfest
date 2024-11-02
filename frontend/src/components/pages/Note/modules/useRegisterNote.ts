// frontend/src/hooks/useRegisterBook.ts
import { createHeader } from '@/common/util/api-util';
import axios from 'axios';

type RegisterNoteRequest = { bookId: number; userId: string; content: string };

export const useRegisterNote = () => {
  const registerNote = async (data: RegisterNoteRequest) => {
    try {
      console.log(data);
      const response = await axios.post('http://localhost:8080/api/v1/memo/register', data, {
        headers: createHeader(),
      });
      console.log(response.data);
      return response.data;
    } catch (error) {
      console.error('Error registering note:', error);
      throw error;
    }
  };

  return { registerNote };
};
