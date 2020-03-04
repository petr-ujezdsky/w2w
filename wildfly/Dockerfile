FROM jboss/wildfly:12.0.0.Final

# user for administration
RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent

# user for EJB calls authentication
RUN /opt/jboss/wildfly/bin/add-user.sh -a ejbUser ejbPassword --silent

RUN mkdir /opt/jboss/.m2
