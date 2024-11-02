import { useState } from 'react';
import { useForm, FormProvider } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { TextField, Select, MenuItem, FormControl, InputLabel, Button } from '@mui/material';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFnsV3';
import styles from './RegisterBookPage.module.scss';
import MainButton from '@/components/atoms/MainButton';
import Header from '@/components/organisms/Header';
import { useNavigate } from 'react-router-dom';
import { registerBookSchema } from './validationSchema';
import { useRegisterBook } from './useRegisterBook';

export type RegisterBookFormValues = {
  // userId: number;
  title: string;
  author: string | null;
  startDate: string | null;
  finishDate: string | null;
  daysPerWeek: string | null;
  image: string | null;
};

const RegisterBookPage = () => {
  const methods = useForm({
    resolver: zodResolver(registerBookSchema),
    defaultValues: {
      // userId: 1,
      title: '',
      author: '',
      startDate: new Date().toISOString().split('T')[0],
      finishDate: '',
      daysPerWeek: '',
      image: null,
    },
  });

  const [fileName, setFileName] = useState<string | null>(null);

  const { registerBook } = useRegisterBook();
  const navigate = useNavigate();

  const handleSave = async (data: RegisterBookFormValues) => {
    try {
      const payload = {
        ...data,
        startDate: data.startDate === '' ? null : data.startDate,
        author: data.author === '' ? null : data.author,
        finishDate: data.finishDate === '' ? null : data.finishDate,
        daysPerWeek: data.daysPerWeek === '' ? null : data.daysPerWeek,
        userId: 'test_user',
      };
      await registerBook(payload);
      navigate('/books');
    } catch (error) {
      console.error('Failed to register book:', error);
    }
  };

  const handleImageChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      setFileName(file.name);
      const reader = new FileReader();
      reader.onloadend = () => {
        methods.setValue('image', reader.result as null);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleGoBack = () => {
    navigate('/books');
  };

  return (
    <LocalizationProvider dateAdapter={AdapterDateFns}>
      <div className={styles.registerBook}>
        <Header handleBackIconClick={handleGoBack} title="Add New Book" />
        <FormProvider {...methods}>
          <form className={styles.form} onSubmit={methods.handleSubmit(handleSave)}>
            <TextField
              label="Title"
              {...methods.register('title')}
              className={styles.input}
              fullWidth
              error={!!methods.formState.errors.title}
              helperText={methods.formState.errors.title?.message}
            />
            <TextField
              label="Author"
              {...methods.register('author')}
              className={styles.input}
              fullWidth
              error={!!methods.formState.errors.author}
              helperText={methods.formState.errors.author?.message}
            />
            <div className={styles.uploadContainer}>
              <Button variant="contained" component="label" className={styles.input}>
                Upload Image
                <input type="file" hidden accept="image/*" onChange={handleImageChange} />
              </Button>
              <p className={styles.fileName}>{methods.watch('image') ? fileName : ''}</p>
            </div>
            <h2 className={styles.sectionTitle}>Schedule</h2>
            <DatePicker
              label="Start date"
              value={new Date(methods.watch('startDate'))}
              onChange={(newValue) =>
                methods.setValue('startDate', newValue?.toISOString().split('T')[0] || '')
              }
              className={styles.input}
              slotProps={{
                textField: {
                  error: !!methods.formState.errors.startDate,
                  helperText: methods.formState.errors.startDate?.message,
                },
              }}
            />
            <DatePicker
              label="Finish date"
              value={new Date(methods.watch('finishDate'))}
              onChange={(newValue) =>
                methods.setValue('finishDate', newValue?.toISOString().split('T')[0] || '')
              }
              className={styles.input}
              slotProps={{
                textField: {
                  error: !!methods.formState.errors.finishDate,
                  helperText: methods.formState.errors.finishDate?.message,
                },
              }}
            />
            <FormControl fullWidth className={styles.input}>
              <InputLabel id="days-per-week-label">Days per week</InputLabel>
              <Select
                labelId="days-per-week-label"
                {...methods.register('daysPerWeek')}
                value={methods.watch('daysPerWeek')}
                onChange={(e) =>
                  methods.setValue('daysPerWeek', (e.target.value as string | null) || '')
                }
              >
                {['', '1', '2', '3', '4', '5', '6', '7'].map((day) => (
                  <MenuItem key={day} value={day}>
                    {day} days
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
            <MainButton type="submit" style={{ marginTop: '24px' }}>
              Save to Library
            </MainButton>
          </form>
        </FormProvider>
      </div>
    </LocalizationProvider>
  );
};

export default RegisterBookPage;
