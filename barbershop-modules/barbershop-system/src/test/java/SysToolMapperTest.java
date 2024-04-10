import com.barbershop.system.RuoYiSystemApplication;
import com.barbershop.system.domain.SysSystemTool;
import com.barbershop.system.mapper.SysSystemToolMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = {RuoYiSystemApplication.class})
public class SysToolMapperTest {
    @Autowired
    private SysSystemToolMapper sysSystemToolMapper;

    @Test
    public void testSelectSysSystemToolList() {
        SysSystemTool tool = new SysSystemTool();
        tool.setDeptId(105L);
        List<SysSystemTool> toolList = sysSystemToolMapper.selectSysSystemToolList(tool);
        toolList.forEach(System.out::println);

    }
}