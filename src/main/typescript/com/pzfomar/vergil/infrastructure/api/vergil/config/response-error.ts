export default interface ResponseError {
  error: string;
  message: string;
  path: string;
  requestId: string;
  status: number;
  timestamp: string;
  trace: string;
}
