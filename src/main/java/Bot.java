import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public String getBotUsername() {
        //получение имени бота
        return "StrellBot";
    }

    public String getBotToken() {
        //получение токена бота
        return "657391723:AAFV9KoWG3VGLL0Y2ZfOKmM1LynzQfIkJXM";
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/help":
                    sendMsg(message, "Бот написаныый на Java, цель которого пока неясна.В разработке учавствует 3 программиста и 1 наблудатель");
                    break;
                case "/about":
                    sendMsg(message, "Тут будет инфа о боте");
                    break;
                case "/trigger":
                    sendMsg(message, "@Дьяк как думаешь налоги в Америке высокие?");
                    break;
                case "/say anything":
                    sendMsg(message, String.valueOf((int) (Math.random()*1000)));
                    break;
                default:
                    break;
            }
        }
    }


    public void setButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        // связываем сообщение с клавиатурой
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        // всем пользователям, или только определенным
        replyKeyboardMarkup.setSelective(true);
        // подгонка клавиатуры под количество кнопок
        replyKeyboardMarkup.setResizeKeyboard(true);
        // скрывть после нажатия кнопки или нет
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add("/about");

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add("/trigger");
        keyboardSecondRow.add("/say anything");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }


    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        // устанавливаем, в какой чат отправить ответ
        sendMessage.setChatId(message.getChatId().toString());
        // устанавливаем, на какое сообщение ответить
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
