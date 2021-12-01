window.onload = function() {
    getCurrentList()
    const createItemButton = document.querySelector( '#create-item' )
    createItemButton.onclick = createItem
}