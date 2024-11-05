export async function alertError(error: Error | TypeError): Promise<void> {
  let alert: any = document.createElement("ion-alert");
  alert.header = "Upppsss";
  alert.message = error.message;
  alert.buttons = ["Closet"];
  document.body.appendChild(alert);
  await alert.present();
}
