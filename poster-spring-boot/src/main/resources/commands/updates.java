package commands;

import org.crsh.cli.Command;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand;
import org.pti.poster.service.impl.ShellCommandsService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.Date;

public class updates extends BaseCommand{

    @Usage("Retrieves what was posted today")
    @Command
    public Object main() {
        ShellCommandsService service = ((DefaultListableBeanFactory)context.getAttributes().get("spring.beanfactory")).getBean(ShellCommandsService.class);
        return service.getPostsForASpecificDate(new Date());
    }
}