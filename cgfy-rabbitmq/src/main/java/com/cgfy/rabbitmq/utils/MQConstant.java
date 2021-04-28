package com.cgfy.rabbitmq.utils;
/**
* @Description  rabbit mq api描述(仅供参考)
* @Author liuyd
* @Date 2019/11/6
**/
public class MQConstant {
	public static final String 	OVERVIEW= "/api/overview";
	public static final String  CLUSTER_NAME= "/api/cluster-name";
	public static final String  NODES= "/api/nodes";
	public static final String  NODES_NAME= "/api/nodes/name";
	public static final String  EXTENSIONS = "/api/extensions";
	public static final String  DEFINITIONS= "/api/definitions";
	public static final String  ALL_CONFIGURATION= "/api/all-configuration";//(deprecated)
	public static final String  DEFINITIONS_VHOST= "/api/definitions/vhost";
	public static final String  CONNECTIONS= "/api/connections";
	public static final String  VHOSTS_VHOST_CONNECTIONS= "/api/vhosts/vhost/connections";
	public static final String  CONNECTIONS_NAME= "/api/connections/name";
	public static final String  CONNECTIONS_NAME_CHANNELS= "/api/connections/name/channels";
	public static final String  CHANNELS= "/api/channels";
	public static final String  VHOSTS_VHOST_CHANNELS= "/api/vhosts/vhost/channels";
	public static final String  CHANNELS_CHANNEL= "/api/channels/channel";
	public static final String  CONSUMERS= "/api/consumers";
	public static final String  CONSUMERS_VHOST= "/api/consumers/vhost";
	/*exchange*/
	public static final String  EXCHANGES= "/api/exchanges";
	public static final String  EXCHANGES_VHOST= "/api/exchanges/vhost";
	public static final String  EXCHANGES_VHOST_NAME= "/api/exchanges/vhost/name";
	public static final String  EXCHANGES_VHOST_NAME_BINDINGS_SOURCE= "/api/exchanges/vhost/name/bindings/source";
	public static final String  ECHANGES_VHOST_NAME_BINDINGS_DESTINATION= "/api/exchanges/vhost/name/bindings/destination";
	public static final String  EXCHANGES_VHOST_NAME_PUBLISH= "/api/exchanges/vhost/name/publish";
	/*queue*/
	public static final String  QUEUES= "/api/queues"; //获取全部队列信息
	public static final String  QUEUES_VHOST= "/api/queues/vhost";
	public static final String  QUEUES_VHOST_NAME= "/api/queues/vhost/name";
	public static final String  QUEUES_VHOST_NAME_BINDINGS= "/api/queues/vhost/name/bindings";
	public static final String  QUEUES_VHOST_NAME_CONTENTS= "/api/queues/vhost/name/contents";
	public static final String  QUEUES_VHOST_NAME_ACTIONS= "/api/queues/vhost/name/actions";
	public static final String  QUEUES_VHOST_NAME_GET= "/api/queues/vhost/name/get";
	/*binding*/
	public static final String  BINDINGS= "/api/bindings";
	public static final String  BINDINGS_VHOST= "/api/bindings/vhost";
	public static final String  BINDINGS_VHOST_E_EXCHANGE_Q_QUEUE= "/api/bindings/vhost/e/exchange/q/queue";
	public static final String  BINDINGS_VHOST_E_EXCHANGE_Q_QUEUE_PROPS= "/api/bindings/vhost/e/exchange/q/queue/props";
	public static final String  BINDINGS_VHOST_E_SOURCE_E_DESTINATION= "/api/bindings/vhost/e/source/e/destination";
	public static final String  BINDINGS_VHOST_E_SOURCE_EDESTINATION_PROPS= "/api/bindings/vhost/e/source/e/destination/props";
	/*vhost*/
	public static final String  VHOSTS= "/api/vhosts";
	public static final String  VHOSTS_NAME= "/api/vhosts/name";
	public static final String  VHOSTS_NAME_PERMISSIONS= "/api/vhosts/name/permissions";
	public static final String  VHOSTS_NAME_TOPIC_PERMISSIONS= "/api/vhosts/name/topic-permissions";
	public static final String  VHOSTS_NAME_START_NODE= "/api/vhosts/name/start/node";
	/*user*/
	public static final String  USERS= "/api/users/";
	public static final String  USER_WITHOUT_PERMISSIONS= "/api/users/without-permissions";
	public static final String  USERS_BULK_DELETE= "/api/users/bulk-delete";
	public static final String  USERS_NAME= "/api/users/name";
	public static final String  USERS_USER_PERMISSIONS= "/api/users/user/permissions";
	public static final String  USERS_USER_TOPIC_PERMISSIONS= "/api/users/user/topic-permissions";

	public static final String  WHOAMI= "/api/whoami";
	public static final String  PERMISSIONS= "/api/permissions";
	public static final String  PERMISSIONS_VHOST_USER= "/api/permissions/vhost/user";
	public static final String  TOPIC_PERMISSIONS= "/api/topic-permissions";
	public static final String  TOPIC_PERMISSIONS_VHOST_USEER= "/api/topic-permissions/vhost/user";
	public static final String  PARAMETERS= "/api/parameters";
	public static final String  PARAMETERS_COMPONENT= "/api/parameters/component";
	public static final String  PARAMETERS_COMPONENT_VHOST= "/api/parameters/component/vhost";
	public static final String  PARAMETERS_COMPONENT_VHOST_NAME= "/api/parameters/component/vhost/name";
	public static final String  GLOBAL_PARANTERS= "/api/global-parameters";
	public static final String  GLOBAL_PARAMETERS_NAME= "/api/global-parameters/name";
	public static final String  POLICIES= "/api/policies";
	public static final String  POLICIES_VHOST= "/api/policies/vhost";
	public static final String  POLICIES_VHOST_NAME= "/api/policies/vhost/name";
	public static final String  OPERATOR_POLICIES= "/api/operator-policies";
	public static final String  OPERATOR_POLICIES_VHOST= "/api/operator-policies/vhost";
	public static final String  OPERATOR_POLICIES_VHOST_NAME= "/api/operator-policies/vhost/name";
	public static final String  ALIVENESS_TEST_VHOST= "/api/aliveness-test/vhost";
	public static final String  HEALTHCHECKS_NODE= "/api/healthchecks/node";
	public static final String  HEALTHCHECKS_NODE_NODE= "/api/healthchecks/node/node";
	public static final String  VHOST_LIMITS= "/api/vhost-limits";
	public static final String  VHOST_LIMITS_VHOST= "/api/vhost-limits/vhost";
	public static final String VHOST_LIMITS_VHOST_NAME = "/api/vhost-limits/vhost/name";

	public static String getOVERVIEW() {
		return OVERVIEW;
	}

	public static String getClusterName() {
		return CLUSTER_NAME;
	}

	public static String getNODES() {
		return NODES;
	}

	public static String getNodesName() {
		return NODES_NAME;
	}

	public static String getEXTENSIONS() {
		return EXTENSIONS;
	}

	public static String getDEFINITIONS() {
		return DEFINITIONS;
	}

	public static String getAllConfiguration() {
		return ALL_CONFIGURATION;
	}

	public static String getDefinitionsVhost() {
		return DEFINITIONS_VHOST;
	}

	public static String getCONNECTIONS() {
		return CONNECTIONS;
	}

	public static String getVhostsVhostConnections() {
		return VHOSTS_VHOST_CONNECTIONS;
	}

	public static String getConnectionsName() {
		return CONNECTIONS_NAME;
	}

	public static String getConnectionsNameChannels() {
		return CONNECTIONS_NAME_CHANNELS;
	}

	public static String getCHANNELS() {
		return CHANNELS;
	}

	public static String getVhostsVhostChannels() {
		return VHOSTS_VHOST_CHANNELS;
	}

	public static String getChannelsChannel() {
		return CHANNELS_CHANNEL;
	}

	public static String getCONSUMERS() {
		return CONSUMERS;
	}

	public static String getConsumersVhost() {
		return CONSUMERS_VHOST;
	}

	public static String getEXCHANGES() {
		return EXCHANGES;
	}

	public static String getExchangesVhost() {
		return EXCHANGES_VHOST;
	}

	public static String getExchangesVhostName() {
		return EXCHANGES_VHOST_NAME;
	}

	public static String getExchangesVhostNameBindingsSource() {
		return EXCHANGES_VHOST_NAME_BINDINGS_SOURCE;
	}

	public static String getEchangesVhostNameBindingsDestination() {
		return ECHANGES_VHOST_NAME_BINDINGS_DESTINATION;
	}

	public static String getExchangesVhostNamePublish() {
		return EXCHANGES_VHOST_NAME_PUBLISH;
	}

	public static String getQUEUES() {
		return QUEUES;
	}

	public static String getQueuesVhost() {
		return QUEUES_VHOST;
	}

	public static String getQueuesVhostName() {
		return QUEUES_VHOST_NAME;
	}

	public static String getQueuesVhostNameBindings() {
		return QUEUES_VHOST_NAME_BINDINGS;
	}

	public static String getQueuesVhostNameContents() {
		return QUEUES_VHOST_NAME_CONTENTS;
	}

	public static String getQueuesVhostNameActions() {
		return QUEUES_VHOST_NAME_ACTIONS;
	}

	public static String getQueuesVhostNameGet() {
		return QUEUES_VHOST_NAME_GET;
	}

	public static String getBINDINGS() {
		return BINDINGS;
	}

	public static String getBindingsVhost() {
		return BINDINGS_VHOST;
	}

	public static String getBindingsVhostEExchangeQQueue() {
		return BINDINGS_VHOST_E_EXCHANGE_Q_QUEUE;
	}

	public static String getBindingsVhostEExchangeQQueueProps() {
		return BINDINGS_VHOST_E_EXCHANGE_Q_QUEUE_PROPS;
	}

	public static String getBindingsVhostESourceEDestination() {
		return BINDINGS_VHOST_E_SOURCE_E_DESTINATION;
	}

	public static String getBindingsVhostESourceEdestinationProps() {
		return BINDINGS_VHOST_E_SOURCE_EDESTINATION_PROPS;
	}

	public static String getVHOSTS() {
		return VHOSTS;
	}

	public static String getVhostsName() {
		return VHOSTS_NAME;
	}

	public static String getVhostsNamePermissions() {
		return VHOSTS_NAME_PERMISSIONS;
	}

	public static String getVhostsNameTopicPermissions() {
		return VHOSTS_NAME_TOPIC_PERMISSIONS;
	}

	public static String getVhostsNameStartNode() {
		return VHOSTS_NAME_START_NODE;
	}

	public static String getUSERS() {
		return USERS;
	}

	public static String getUserWithoutPermissions() {
		return USER_WITHOUT_PERMISSIONS;
	}

	public static String getUsersBulkDelete() {
		return USERS_BULK_DELETE;
	}

	public static String getUsersName() {
		return USERS_NAME;
	}

	public static String getUsersUserPermissions() {
		return USERS_USER_PERMISSIONS;
	}

	public static String getUsersUserTopicPermissions() {
		return USERS_USER_TOPIC_PERMISSIONS;
	}

	public static String getWHOAMI() {
		return WHOAMI;
	}

	public static String getPERMISSIONS() {
		return PERMISSIONS;
	}

	public static String getPermissionsVhostUser() {
		return PERMISSIONS_VHOST_USER;
	}

	public static String getTopicPermissions() {
		return TOPIC_PERMISSIONS;
	}

	public static String getTopicPermissionsVhostUseer() {
		return TOPIC_PERMISSIONS_VHOST_USEER;
	}

	public static String getPARAMETERS() {
		return PARAMETERS;
	}

	public static String getParametersComponent() {
		return PARAMETERS_COMPONENT;
	}

	public static String getParametersComponentVhost() {
		return PARAMETERS_COMPONENT_VHOST;
	}

	public static String getParametersComponentVhostName() {
		return PARAMETERS_COMPONENT_VHOST_NAME;
	}

	public static String getGlobalParanters() {
		return GLOBAL_PARANTERS;
	}

	public static String getGlobalParametersName() {
		return GLOBAL_PARAMETERS_NAME;
	}

	public static String getPOLICIES() {
		return POLICIES;
	}

	public static String getPoliciesVhost() {
		return POLICIES_VHOST;
	}

	public static String getPoliciesVhostName() {
		return POLICIES_VHOST_NAME;
	}

	public static String getOperatorPolicies() {
		return OPERATOR_POLICIES;
	}

	public static String getOperatorPoliciesVhost() {
		return OPERATOR_POLICIES_VHOST;
	}

	public static String getOperatorPoliciesVhostName() {
		return OPERATOR_POLICIES_VHOST_NAME;
	}

	public static String getAlivenessTestVhost() {
		return ALIVENESS_TEST_VHOST;
	}

	public static String getHealthchecksNode() {
		return HEALTHCHECKS_NODE;
	}

	public static String getHealthchecksNodeNode() {
		return HEALTHCHECKS_NODE_NODE;
	}

	public static String getVhostLimits() {
		return VHOST_LIMITS;
	}

	public static String getVhostLimitsVhost() {
		return VHOST_LIMITS_VHOST;
	}

	public static String getVhostLimitsVhostName() {
		return VHOST_LIMITS_VHOST_NAME;
	}
}
