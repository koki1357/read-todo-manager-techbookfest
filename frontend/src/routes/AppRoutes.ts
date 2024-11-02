import BookDetailPage from '@/components/pages/BookDetail/BookDetailPage';
import LibraryPage from '@/components/pages/Library/LibraryPage';
import Login from '@/components/pages/Login/Login';
import NotePage from '@/components/pages/Note/NotePage';
import RegisterBookPage from '@/components/pages/RegisterBook/RegisterBookPage';
import { RouteObject } from 'react-router';

export const routes: RouteObject[] = [
  {
    path: '/login',
    Component: Login,
  },
  {
    path: '/books',
    Component: LibraryPage,
  },
  {
    path: '/books/register',
    Component: RegisterBookPage,
  },
  {
    path: '/books/:id',
    Component: BookDetailPage,
  },
  {
    path: '/books/:id/note',
    Component: NotePage,
  },
];
