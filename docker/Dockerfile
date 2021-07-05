
FROM jboss/wildfly

USER root

ADD https://jdbc.postgresql.org/download/postgresql-42.2.22.jar /tmp/postgresql-42.2.22.jar

WORKDIR /tmp
COPY docker-helper-scripts/wildfly-command.sh ./
COPY docker-helper-scripts/module-install.cli ./
# search and replace
RUN sed -i -e 's/\r$//' ./wildfly-command.sh
RUN chmod +x ./wildfly-command.sh
RUN ./wildfly-command.sh \
    &&  rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/

ADD patient-transfer.war /opt/jboss/wildfly/standalone/deployments/

# Create Wildfly admin user
RUN $JBOSS_HOME/bin/add-user.sh admin admin --silent

# Set the default command to run on boot
# This will boot WildFly in the standalone mode and bind to all interface
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]


