import { createHeader } from '@/common/util/api-util';
import { errorAtom } from '@/components/organisms/error-modal/ErrorModal';
import axios from 'axios';
import { useAtom } from 'jotai';

type RegisterUserRequest = {
  userId: string;
  password: string;
};

export const useRegisterUser = () => {
  const [, setErrorState] = useAtom(errorAtom);

  const registerUser = async (request: RegisterUserRequest) => {
    try {
      // ユーザー登録処理
      await axios.post('http://localhost:8080/api/auth/user', request, {
        headers: createHeader(),
      });
      return;
    } catch (error: any) {
      setErrorState({
        hasError: true,
        messages: [error.response.data.message],
      });
      throw error;
    }
  };

  return { registerUser };
};
