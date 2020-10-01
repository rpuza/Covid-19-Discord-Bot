import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.util.*;
import javax.security.auth.login.LoginException;

public class main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "NzUzNjk5NjUzODE1NTAwOTIw.X1p_gA.pRobNCcl11A_kPjrOvivZnCy3sg";
        builder.setToken(token);
        builder.addEventListener(new main());
        builder.buildAsync();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println("We received a message from " +
                event.getAuthor().getName() + ": " +
                event.getMessage().getContentDisplay());
        if(event.getMessage().getContentRaw().toLowerCase().equals("say hello bro")){
            event.getChannel().sendMessage("Hello World!").queue();
        }
    }
}
