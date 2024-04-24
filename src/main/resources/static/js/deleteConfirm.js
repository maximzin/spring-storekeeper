const btns = document.getElementsByClassName("formButton");
const deleteBtn = btns.item(0);
deleteBtn.addEventListener("click", () => {
    const result = confirm("Подтвердите удаление, инструмент будет удалён из всех шкафов");
});