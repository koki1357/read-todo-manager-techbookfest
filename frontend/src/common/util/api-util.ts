export const createHeader = () => {
  const token = localStorage.getItem('token');
  // ろーカルストレージからユーザーIDを取得
  const userId = localStorage.getItem('userId');

  return {
    userId: userId,
    token: token,
  };
};
