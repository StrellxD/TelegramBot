import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {


        Message message = update.getMessage();
        if(message != null && message.hasText()){
            switch (message.getText()) {
                case "/about":
                    sendMsg(update.getMessage().getChatId().toString(), "Тестовый бот от Strell, написаныый на Java. Автор готов выслушать предложения для реализации");
                    break;
                case "/help":
                    sendMsg(update.getMessage().getChatId().toString(), "Доступные команды бота:\n/about\n/help\n/diack");
                    break;
                case "/diack":
                    sendMsg(update.getMessage().getChatId().toString(), "Бот отправляется в слуцк");
                    break;
                case "/bot":
                    sendMsg(update.getMessage().getChatId().toString(), "Дьяк, го делать ботаfsdfsdfsdfdsfdsfsdfsd");
                    break;
            }
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
