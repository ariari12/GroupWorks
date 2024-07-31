let editor;
document.addEventListener('DOMContentLoaded', function() {
    const initialData = {"time":1721196893552,"blocks":[{"id":"4n_vPBS0yd","type":"header","data":{"text":"안녕하세요!","level":1}},{"id":"xvRUXpjrQk","type":"paragraph","data":{"text":"반갑습니다."}},{"id":"oD_L4RkfbP","type":"paragraph","data":{"text":"ㄴㅁㄴㅇㄴㅁㅇ!"}}],"version":"2.30.2"};

    editor = new EditorJS({
        readOnly: false,
        data: initialData,
        holder: 'editorjs',
        tools: {
            header: {
                class: Header,
                inlineToolbar: ['link'],
                config: {
                    placeholder: '제목을 입력하세요'
                },
                shortcut: 'CMD+SHIFT+H'
            },
            list: {
                class: List,
                inlineToolbar: true,
                shortcut: 'CMD+SHIFT+L'
            },
            checklist: {
                class: Checklist,
                inlineToolbar: true,
            },
            code: {
                class: CodeTool,
                shortcut: 'CMD+SHIFT+C'
            },
            delimiter: Delimiter,
            inlineCode: {
                class: InlineCode,
                shortcut: 'CMD+SHIFT+I'
            },
            table: {
                class: Table,
                inlineToolbar: true,
                shortcut: 'CMD+ALT+T'
            },
        },
    });
});

// Editor.js
async function preview() {
    try {
        // Editor.js에서 데이터 추출
        const outputData = await editor.save();

        // JSON 데이터를 HTML로 변환
        const html = outputData.blocks.map(block => {
            switch (block.type) {
                case 'header':
                    return `<h${block.data.level}>${block.data.text}</h${block.data.level}>`;
                case 'paragraph':
                    return `<p>${block.data.text}</p>`;
                case 'list':
                    return `<ol>${block.data.items.map(item => `<li>${item}</li>`).join('')}</ol>`;
                case 'checklist':
                    return `<ul>${block.data.items.map(item => `<li><input type="checkbox"${item.checked ? ' checked' : ''}> ${item.text}</li>`).join('')}</ul>`;
                case 'code':
                    return `<pre><code>${block.data.code}</code></pre>`;
                case 'table':
                    return `<table class="table table-bordered">${block.data.content.map(row => `<tr>${row.map(cell => `<td>${cell}</td>`).join('')}</tr>`).join('')}</table>`;
                case 'delimiter':
                    return `<hr />`;
                default:
                    return '';
            }
        }).join('');

        // 타이틀 변경
        document.getElementById('titlePreview').innerHTML =
            '<h5>' +
            subject() + ' ' +
            '<span>' +
            ($('#title').val().trim() == "" ? "제목 미리보기" : $('#title').val()) +
            '</span>' +
            '</h5>';

        document.getElementById('datePreview').innerHTML =
            '<b>작성일</b> : ' + formatDate() +
            '<span class="badge rounded-pill text-bg-custom" style="float: right; margin-left: 5px;">댓글 0</span>' +
            '<span class="badge rounded-pill text-bg-custom" style="float: right">조회수 0</span>';
        document.getElementById('contentPreview').innerHTML = html;

        // 모달 표시
        var myModal = new bootstrap.Modal(document.getElementById('exampleModal'), {
            keyboard: true
        });
        myModal.show();

        // 콜랩스
        var collapseElement = document.getElementById('collapseExample');
        var bsCollapse = new bootstrap.Collapse(collapseElement, {
            toggle: false
        });
        bsCollapse.hide();

        var elements = $('[data-file="file"]');
        $('#attachFileList').html(
            `<div class="collapse" id="collapseExample" style="margin: 30px; position: absolute; right: 17%; transform: translateX( 5% );">
                <div class="card card-body" id="preview-file">
                </div>
            </div>`
        );
        // 첨부파일 비우기.
        $('#preview-file').empty();

        if (elements.length !== 0) {
            $('#attachFileList').append(
                `<span data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-folder" viewBox="0 0 16 16">
                        <path d="M.54 3.87.5 3a2 2 0 0 1 2-2h3.672a2 2 0 0 1 1.414.586l.828.828A2 2 0 0 0 9.828 3h3.982a2 2 0 0 1 1.992 2.181l-.637 7A2 2 0 0 1 13.174 14H2.826a2 2 0 0 1-1.991-1.819l-.637-7a2 2 0 0 1 .342-1.31zM2.19 4a1 1 0 0 0-.996 1.09l.637 7a1 1 0 0 0 .995.91h10.348a1 1 0 0 0 .995-.91l.637-7A1 1 0 0 0 13.81 4zm4.69-1.707A1 1 0 0 0 6.172 2H2.5a1 1 0 0 0-1 .981l.006.139q.323-.119.684-.12h5.396z"/>
                    </svg>
                    첨부파일
                </span>`
            );
        }

        // 각 요소의 텍스트를 콘솔에 출력
        elements.each(function() {
            var cleanedText = $(this).text().replace(/×| /g, '').trim();

            // 앞에서부터 20자리 가져오기
            var first20Chars = cleanedText.substring(0, 20);

            // 확장자 가져오기
            var extensionMatch = cleanedText.match(/\.[0-9a-z]+$/i);
            var extension = extensionMatch ? extensionMatch[0] : '';

            // 중간 부분을 생략하여 축약된 파일 이름 만들기
            var maxLength = 20;
            var truncatedText = cleanedText;

            if (cleanedText.length > maxLength) {
                var start = cleanedText.substring(0, 10);
                var end = extension ? cleanedText.substring(cleanedText.length - extension.length - 10, cleanedText.length - extension.length) : cleanedText.substring(cleanedText.length - 10);
                truncatedText = start + '...' + end + extension;
            }
            // 첨부파일 추가.
            $('#preview-file').append('<span>' + truncatedText + ' | <a href="#">다운로드</a></span>');
        });
    } catch (error) {
        console.error('Error while saving editor data:', error);
    }
}

// 말머리 배지 설정.
function subject() {
    switch ($('#subject').val()) {
        case 'suggestion':
            return '<span class="badge text-bg-primary">제안</span>';
        case 'inquiry':
            return '<span class="badge text-bg-success">문의</span>';
        case 'error':
        case 'announcement':
        case 'mandatory':
            return '<span class="badge text-bg-danger">' +
                $("#subject option:selected").text() + '</span>';
        default:
            return '<span class="badge text-bg-secondary">일반</span>';
    }
}

// JSON 으로 저장
function test() {
    editor.save().then((outputData) => {
        console.log('Article data:', outputData);
        let a = JSON.stringify(outputData)
        console.log(a);
    });
}