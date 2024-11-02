import MainButton from '@/components/atoms/MainButton';
import Header from '@/components/organisms/Header';
import TextField from '@mui/material/TextField';
import { useState } from 'react';
import { FormProvider, useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import styles from './Login.module.scss';
import { useLogin } from './useLogin';
import { useRegisterUser } from './useRegisterUser';

interface LoginProps {}

const Login = () => {
  const [userId, setUserId] = useState('');
  const [password, setPassword] = useState('');
  const [createUserState, setCreateUserState] = useState(false);

  const navigate = useNavigate();
  const { login } = useLogin();
  const { registerUser } = useRegisterUser();

  const forms = useForm({
    defaultValues: {
      userId: '',
      password: '',
    },
  });

  const handleLogin = async () => {
    const payload = {
      userId: forms.getValues('userId'),
      password: forms.getValues('password'),
    };
    await login(payload);

    navigate('/books');
  };

  const handleCreateUser = async () => {
    const payload = {
      userId: forms.getValues('userId'),
      password: forms.getValues('password'),
    };

    await registerUser(payload);

    setCreateUserState(true);
  };

  return (
    <div className={styles.container}>
      <Header title="ログイン" hideBackIcon={true} />
      <div className={styles.contents}>
        <FormProvider {...forms}>
          <div>
            <TextField
              type="text"
              placeholder="User ID"
              value={userId}
              {...forms.register('userId')}
              onChange={(e) => setUserId(e.target.value)}
            />
          </div>
          <div className={styles.password}>
            <TextField
              type="password"
              placeholder="Password"
              value={password}
              {...forms.register('password')}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
        </FormProvider>
        <MainButton type="submit" style={{ marginTop: '24px' }} onClick={handleLogin}>
          ログイン
        </MainButton>
        <MainButton type="submit" style={{ marginTop: '40px' }} onClick={handleCreateUser}>
          ユーザーを登録
        </MainButton>
        {createUserState && <div className={styles.message}>ユーザ登録しました！</div>}
      </div>
    </div>
  );
};

export default Login;
