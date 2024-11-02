import { useParams, useNavigate } from 'react-router-dom';
import BookInfo from './modules/BookInfo';
import NoteForm from './modules/NoteForm';
// import NoteList from './modules/NoteList';
import styles from './NotePage.module.scss';
import Header from '@/components/organisms/Header';
import useGetBookDetail from '../BookDetail/useGetBookDetail';
import ApiHandlerContainer from '@/components/organisms/ApiHandlerContainer';

const NotePage: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  // const [notes, setNotes] = useState<string[]>([]);
  const { book, loading, error } = useGetBookDetail();

  const navigate = useNavigate();

  const handleGoBack = () => {
    navigate(`/books/${id}`); // 適切なパスに変更してください
  };

  return (
    <ApiHandlerContainer loading={loading} error={error}>
      <div className={styles.notesPage}>
        <Header handleBackIconClick={handleGoBack} title="Create Note" />
        {book && <BookInfo title={book.title} author={book.author} image={book.image} />}
        <p className={styles.description}>
          Notes are private and only visible to you. You can refer back to them at any time.
        </p>
        {id ? <NoteForm bookId={parseInt(id)} /> : '本が見当たりません'}
        {/* ここでもメモ一覧を表示するのはめんどいから一旦やめる */}
        {/* <NoteList notes={notes} /> */}
      </div>
    </ApiHandlerContainer>
  );
};

export default NotePage;
