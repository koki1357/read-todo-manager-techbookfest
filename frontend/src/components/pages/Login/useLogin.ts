import { errorAtom } from '@/components/organisms/error-modal/ErrorModal';
import axios from 'axios';
import { useAtom } from 'jotai';

type LoginRequest = {
  userId: string;
  password: string;
};

type LoginResponse = {
  token: string;
  userId: string;
};

export const useLogin = () => {
  const [, setErrorState] = useAtom(errorAtom);

  const login = async (request: LoginRequest) => {
    try {
      console.log(request);
      const response = await axios.post('http://localhost:8080/api/auth/login', request);

      const data: LoginResponse = response.data.data;

      // ローカルストレージにトークンを保存
      localStorage.setItem('token', data?.token);
      // ローカルストレージにユーザーIDを保存
      localStorage.setItem('userId', data?.userId);

      return data;
    } catch (error: any) {
      setErrorState({
        hasError: true,
        messages: [error.response.data.message],
      });
      throw error;
    }
  };
  return { login };
};
