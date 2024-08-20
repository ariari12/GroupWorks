// 게시글 작성일
function formatDate() {
    const now = new Date();

    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const day = String(now.getDate()).padStart(2, '0');
    const hours = now.getHours();
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');

    const ampm = hours >= 12 ? '오후' : '오전';
    const displayHours = hours % 12 || 12;

    const formattedDate = `${year}-${month}-${day} ${ampm} ${displayHours}:${minutes}:${seconds}`;
    return formattedDate;
}