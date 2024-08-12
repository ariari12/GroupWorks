$(document).ready(function() {
    document.querySelectorAll('tbody tr').forEach(row => {
        row.addEventListener('mouseover', function() {
            const fifthCell = row.children[11]; // 5th cell (index 4)
            const span = fifthCell.querySelector('span');
            if (span) {
                if (span.classList.contains('approval')) {
                    span.classList.remove('approval');
                    span.classList.add('approval-hover');
                } else if (span.classList.contains('rejection')) {
                    span.classList.remove('rejection');
                    span.classList.add('rejection-hover');
                }
            }
        });

        row.addEventListener('mouseout', function() {
            const fifthCell = row.children[11]; // 5th cell (index 4)
            const span = fifthCell.querySelector('span');
            if (span) {
                if (span.classList.contains('approval-hover')) {
                    span.classList.remove('approval-hover');
                    span.classList.add('approval');
                } else if (span.classList.contains('rejection-hover')) {
                    span.classList.remove('rejection-hover');
                    span.classList.add('rejection');
                }
            }
        });
    });
});
