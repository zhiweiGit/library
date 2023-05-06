$(".sid").each(function () {
    if ($(this).text() === "1") {
        $(this).text("计算机类");
    } else if ($(this).text() === "2") {
        $(this).text("数学类");
    } else if ($(this).text() === "3") {
        $(this).text("英语类");
    } else if ($(this).text() === "4") {
        $(this).text("语文类");
    } else if ($(this).text() === "5") {
        $(this).text("生活类");
    }
})