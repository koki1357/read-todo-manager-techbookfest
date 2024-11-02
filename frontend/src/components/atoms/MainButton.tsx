import React from 'react';
import styles from './MainButton.module.scss';

type MainButtonProps = React.ButtonHTMLAttributes<HTMLButtonElement> & {
  onClick?: () => void;
  children: React.ReactNode;
};

const MainButton: React.FC<MainButtonProps> = ({ onClick, children, ...rest }) => (
  <button onClick={onClick} className={styles.button} {...rest}>
    {children}
  </button>
);

export default MainButton;
