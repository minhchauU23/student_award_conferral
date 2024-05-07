let previousLogTaskCount = 0;
let isEndTaskFound = false; // Biến để kiểm soát khi nào dừng việc thêm step vào timeline
 const stepNames = [
        "CREATED",
        "GET_EVENT_DESCRIPTION",
        "VERIFY_EVENT",
        "GET_STUDENT_TRANSCRIPT",
        "GET_AWARD_RULE",
        "VERIFY_STUDENT_TRANSCRIPT",
        "UPDATE_STUDENT_TRANSCRIPT",
        "UPDATE_AWARD_HISTORY",
        "SEND_NOTIFICATION_SUCCESS",
        "END_TASK"
    ];

function callAPI(taskId) {
    const apiUrl = `http://127.0.0.1:9996/confer_student_award/task/${taskId}`;

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Có lỗi khi gọi API');
            }
            return response.json();
        })
        .then(data => {
            const logTasks = data.result.logTasks;
             if (logTasks.length > previousLogTaskCount && !isEndTaskFound) {
                logTasks.sort((a, b) => {
                    return new Date(a.time) - new Date(b.time);
                });

                const timeline = document.querySelector('.timeline');

                for (let i = previousLogTaskCount; i < logTasks.length; i++) {
                    const logTask = logTasks[i];

                    const step = document.createElement('div');
                    step.classList.add('step');
                    step.innerHTML = `
                       <div class="content">
                           <h3 class="name">${logTask.name}</h3>
                           <p class="state">
                               ${logTask.state === "SUCCESS" ? '<i class="fas fa-check-circle"></i>' :
                                 logTask.state === "PENDING" ? '<i class="fas fa-spinner fa-spin"></i>' :
                                 logTask.state === "FAILED" ? '<i class="fas fa-times-circle"></i>' :
                                 ''}
                               ${logTask.state}
                           </p>
                           <p class="time">${convertToDisplayTime(logTask.time)}</p>
                       </div>
                    `;
                    timeline.appendChild(step);

                    // Kiểm tra nếu log task có name là "end_task", đặt biến isEndTaskFound thành true
                    if (logTask.name === "END_TASK") {
                        isEndTaskFound = true;
                        break; // Dừng vòng lặp nếu tìm thấy log task kết thúc
                    }
                }

                previousLogTaskCount = logTasks.length;
            }

            if (!isEndTaskFound) {
                setTimeout(() => {
                    callAPI(taskId);
                }, 6000); // Thời gian đợi (10 giây)
            }
        })
        .catch(error => {
            console.error('Lỗi khi gọi API:', error);
        });
}

document.addEventListener('DOMContentLoaded', function() {
    const taskIdInput = document.querySelector('input[type="hidden"]');
    const taskId = taskIdInput.value;

    callAPI(taskId);
});

const convertToDisplayTime = (timestamp) => {
    const date = new Date(timestamp);
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear();
    return `${hours}:${minutes} ${day}/${month}/${year}`;
};