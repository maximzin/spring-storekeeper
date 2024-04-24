const deleteBtn = document.getElementsByClassName("formButtonHidden");
const concrDeleteBtn = deleteBtn.item(0);
const pressingBtn = document.getElementsByClassName("formButton");
const concrPressBtn = pressingBtn.item(0);
concrPressBtn.addEventListener("click", () => {
    const result = confirm("Подтвердите удаление, будут удалены все связанные объекты");
    if (result===true) {
        concrDeleteBtn.click();
    }
});