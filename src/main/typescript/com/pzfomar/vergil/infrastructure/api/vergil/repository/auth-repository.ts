import { headers } from "../config/headers";
import ResponseError from "../config/response-error";

const host = "http://localhost:8080";

export async function signUp(request: {
  email: string;
  password: string;
  confirm_password: string;
}): Promise<void> {
  let response: Response = await fetch(`${host}/auth/sign-up`, {
    method: "POST",
    headers: headers(),
    body: JSON.stringify(request),
  });
  if (response.status === 201) {
    return;
  } else {
    let responseError = (await response.json()) as unknown as ResponseError;
    throw new Error(responseError.message);
  }
}

export async function signIn(request: {
  email: string;
  password: string;
}): Promise<{ access_token: string }> {
  let response: Response = await fetch(`${host}/auth/sign-in`, {
    method: "POST",
    headers: headers(),
    body: JSON.stringify(request),
  });
  if (response.status === 200) {
    let responseSuccess = (await response.json()) as unknown as {
      access_token: string;
    };
    return responseSuccess;
  } else {
    let responseError = (await response.json()) as unknown as ResponseError;
    throw new Error(responseError.message);
  }
}

export async function signOut(request: {
  email: string;
  password: string;
}): Promise<void> {
  let response: Response = await fetch(`${host}/auth/sign-out`, {
    method: "POST",
    headers: headers(),
    body: JSON.stringify(request),
  });
  if (response.status === 200) {
    return;
  } else {
    let responseError = (await response.json()) as unknown as ResponseError;
    throw new Error(responseError.message);
  }
}

export default { signUp, signIn, signOut };
