import AuthRepository from "../../infrastructure/api/vergil/repository/auth-repository";

function validEmail(email: string): void {
  if (!email || email == null || email.trim() == "") {
    throw new Error("El campo EMAIL esta vacio.");
  }
}

function validPassword(password: string): void {
  if (!password || password == null || password.trim() == "") {
    throw new Error("El campo PASSWORD esta vacio.");
  }
}

export function signIn(
  email: string,
  password: string,
  callbackNav: Function,
  callbackAlertError: Function
): void {
  Promise.resolve()
    .then(() => {
      validEmail(email);
      validPassword(password);
      return { email, password };
    })
    .then(AuthRepository.signIn)
    .then((response) => {
      sessionStorage.setItem("access_token", response.access_token);
      callbackNav();
    })
    .catch((error) => callbackAlertError(error));
}

export default { signIn };
