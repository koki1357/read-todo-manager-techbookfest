import { createHeader } from '@/common/util/api-util';
import axios from 'axios';

export const useLogout = () => {
  const logout = async () => {
    try {
      console.log('logout');
      await axios.post('http://localhost:8080/api/auth/logout', null, {
        headers: createHeader(),
      });

      localStorage.removeItem('token');
      localStorage.removeItem('userId');

      return;
    } catch (error) {
      console.error('Error logging out:', error);
      throw error;
    }
  };
  return { logout };
};
