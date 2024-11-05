export const headers = (): HeadersInit =>
  sessionStorage.getItem("access_token")
    ? {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: `Bearer ${sessionStorage.getItem("access_token")}`,
      }
    : {
        Accept: "application/json",
        "Content-Type": "application/json",
      };
