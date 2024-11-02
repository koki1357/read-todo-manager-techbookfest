import { z } from 'zod';

export const registerBookSchema = z
  .object({
    // userId: z.number(),
    title: z.string().min(1, 'Title is required'),
    author: z.string(),
    startDate: z
      .string()
      .refine((val) => val === '' || /^\d{4}-\d{2}-\d{2}$/.test(val), {
        message: 'Start date must be in YYYY-MM-DD format or empty',
      })
      .nullable(),
    finishDate: z
      .string()
      .refine((val) => val === '' || /^\d{4}-\d{2}-\d{2}$/.test(val), {
        message: 'Finish date must be in YYYY-MM-DD format or empty',
      })
      .nullable(),
    daysPerWeek: z.string().nullable(),
    image: z.string().nullable(),
  })
  .refine(
    (data) => {
      if (data.startDate && data.finishDate) {
        return new Date(data.startDate) <= new Date(data.finishDate);
      }
      return true;
    },
    {
      message: 'Start date must be before or equal to finish date',
      path: ['finishDate'], // エラーメッセージを表示するフィールド
    }
  );
