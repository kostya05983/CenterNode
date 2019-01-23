enum class CodeNotification(val code: Byte) {
    Start(0),
    Join(1),
    Off(2),
    StopAll(3),
    Update(4);
}