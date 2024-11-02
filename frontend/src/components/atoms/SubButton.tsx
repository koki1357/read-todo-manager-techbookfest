import React from 'react';
import styles from './SubButton.module.scss';

interface MainButtonProps {
  onClick: () => void;
  children: React.ReactNode;
}

const SubButton: React.FC<MainButtonProps> = ({ onClick, children }) => (
  <button onClick={onClick} className={styles.button}>
    {children}
  </button>
);

export default SubButton;
