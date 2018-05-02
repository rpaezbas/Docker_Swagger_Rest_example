FROM payara/server-full:173

# Setup configuration
USER payara
COPY postgresql-42.2.2.jar /opt/payara41/glassfish/domains/domain1/lib
COPY domain.xml /opt/payara41/glassfish/domains/domain1/config/domain.xml
COPY ./target/Login-0.0.1-SNAPSHOT  /opt/payara41/glassfish/domains/domain1/autodeploy/Login-0.0.1-SNAPSHOT
                                        
