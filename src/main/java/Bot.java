import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        //выполняется, когда бот получает сообщение
        //update содержит отправленное пользоватетелем сообщение
        String message = update.getMessage().getText();
        //sendMsg(update.getMessage().getChatId().toString(), message);


        int menu = 0;
        if (message.equals("/about"))
            menu = 1;
        else if (message.equals("/help"))
            menu = 2;
        else if (message.equals("/diack"))
            menu = 3;
        else if (message.equals("/bot"))
            menu = 4;
        switch (menu) {
            case 1:
                sendMsg(update.getMessage().getChatId().toString(), "Тестовый бот от Strell, написаныый на Java. Автор готов выслушать предложения для реализации");
                break;
            case 2:
                sendMsg(update.getMessage().getChatId().toString(), "Доступные команды бота:\n/about\n/help\n/diack");
                break;
            case 3:
                sendMsg(update.getMessage().getChatId().toString(), "Бот отправляется в слуцк");
                break;
            case 4:
                sendMsg(update.getMessage().getChatId().toString(), "Дьяк, го делать бота");
                break;
        }
    }

    public String getBotUsername() {
        //получение имени бота
        return "StrellBot";
    }

    public String getBotToken() {
        //получение токена бота
        return "657391723:AAFV9KoWG3VGLL0Y2ZfOKmM1LynzQfIkJXM";
    }

    private synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

//    private synchronized void setButtons(SendMessage sendMessage) {
//        // Создаем клавиуатуру
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        sendMessage.setReplyMarkup(replyKeyboardMarkup);
//        replyKeyboardMarkup.setSelective(true);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//        replyKeyboardMarkup.setOneTimeKeyboard(true);
//
//        // Создаем список строк клавиатуры
//        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
//
//        // Первая строчка клавиатуры
//        KeyboardRow keyboardFirstRow = new KeyboardRow();
//        // Добавляем кнопки в первую строчку клавиатуры
//        keyboardFirstRow.add(new KeyboardButton("Привет"));
//
//        // Вторая строчка клавиатуры
//        KeyboardRow keyboardSecondRow = new KeyboardRow();
//        // Добавляем кнопки во вторую строчку клавиатуры
//        keyboardSecondRow.add(new KeyboardButton("Помощь"));
//
//        // Добавляем все строчки клавиатуры в список
//        keyboard.add(keyboardFirstRow);
//        keyboard.add(keyboardSecondRow);
//        // и устанваливаем этот список нашей клавиатуре
//        replyKeyboardMarkup.setKeyboard(keyboard);
//    }
}
