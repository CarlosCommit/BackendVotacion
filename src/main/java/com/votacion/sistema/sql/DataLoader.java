package com.votacion.sistema.sql;

import com.votacion.sistema.dao.CandidatoDAO;
import com.votacion.sistema.dao.RolDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private RolDAO rolDAO;
    @Autowired
    private CandidatoDAO candidatoDAO;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(rolDAO.findAll().isEmpty())
        {
            ScriptUtils.executeSqlScript(dataSource.getConnection(),new ClassPathResource("sql-scripts/test-data.sql"));
        }
        if(candidatoDAO.findAll().isEmpty())
        {
            ScriptUtils.executeSqlScript(dataSource.getConnection(),new ClassPathResource("sql-scripts/candidatos.sql"));
        }
    }
}
