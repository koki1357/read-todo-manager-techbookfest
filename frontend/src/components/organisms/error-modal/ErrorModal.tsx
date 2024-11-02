import { Button } from '@mui/material';
import { atom, useAtom } from 'jotai';
import styles from './ErrorModal.module.scss';

type ErrorModalAtom = {
  hasError: boolean;
  messages: string[] | null;
};

export const errorAtom = atom<ErrorModalAtom>({
  hasError: false,
  messages: null,
});

const ErrorModal = () => {
  const [errorState, setErrorState] = useAtom(errorAtom);

  const close = () => {
    setErrorState({
      hasError: false,
      messages: null,
    });
  };

  return (
    <>
      {errorState.hasError && (
        <div className={styles.container}>
          <div className={styles.messageContainer}>
            <div className={styles.message}>{errorState.messages?.map((m) => <div>{m}</div>)}</div>
            <Button
              variant="contained"
              className={styles.submitButton}
              color="error"
              onClick={close}
            >
              閉じる
            </Button>
          </div>
        </div>
      )}
    </>
  );
};

export default ErrorModal;
