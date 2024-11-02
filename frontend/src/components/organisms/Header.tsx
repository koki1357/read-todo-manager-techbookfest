import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import LogoutIcon from '@mui/icons-material/Logout';
import { IconButton } from '@mui/material';
import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import styles from './Header.module.scss';
import { useLogout } from './useLogout';

interface HeaderProps {
  hideBackIcon?: boolean;
  handleBackIconClick?: () => void;
  title: string;
}

const Header: React.FC<HeaderProps> = ({ hideBackIcon, handleBackIconClick, title }) => {
  const navigate = useNavigate();
  const { logout } = useLogout();

  const handle = async () => {
    console.log('logout');
    await logout();

    navigate('/login');
  };

  const path = ['/login'];
  const currentPath = useLocation().pathname;

  return (
    <header className={styles.header}>
      {!hideBackIcon && (
        <IconButton aria-label="go back" onClick={handleBackIconClick}>
          <ArrowBackIcon />
        </IconButton>
      )}
      <h1 className={styles.title}>{title}</h1>
      {/* ログイン画面以外でログアウトボタンを表示 */}
      {!path.includes(currentPath) && (
        <LogoutIcon className={styles.logout} onClick={handle}></LogoutIcon>
      )}
    </header>
  );
};

export default Header;
