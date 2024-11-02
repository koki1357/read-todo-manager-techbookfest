import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import './App.css';
import ErrorModal from './components/organisms/error-modal/ErrorModal';
import { routes } from './routes/AppRoutes';

export const App = (): React.ReactElement => {
  const router = createBrowserRouter(routes);

  return (
    <>
      <RouterProvider router={router} />
      <ErrorModal />
    </>
  );
};
export default App;
