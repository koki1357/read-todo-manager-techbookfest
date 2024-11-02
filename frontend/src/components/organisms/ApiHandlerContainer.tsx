// frontend/src/utils/apiStatusHandler.tsx
import React from 'react';

interface ApiHandlerContainerProps {
  loading: boolean;
  error: string | null;
  children: React.ReactNode;
}

const ApiHandlerContainer: React.FC<ApiHandlerContainerProps> = ({ loading, error, children }) => {
  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return <>{children}</>;
};

export default ApiHandlerContainer;
