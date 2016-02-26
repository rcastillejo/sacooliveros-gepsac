angular.module('app.textos', [])
.constant('loginTextos', {
	titulo: 'CONTROL DE ACCESO',
	usuario: 'Usuario : ',
	contrasenia: 'Contrase\u00f1a :',
	ingresarUsuario: 'Ingrese el usuario',
	ingresarContrasenia: 'Ingrese la contrase\u00f1a',
	olvido: '\u00bfOlvid\u00f3 su contrase\u00f1a?',
	ingresar: 'INGRESAR'
})
.constant('formatTexto', {
	//Descripcion de las controles individuales
	titulo: 'FORMATEADOR DE TRAMA',
	//******Descripcion de los Tag
	messageFormatId: 'Identificador:',
	messageFormatDesc: 'Descripcion:',
	transformerConfigFile: 'Archivo transformador:',
	routerConfigFile: 'Archivo roteador:',
	transformerStruct: 'Tipo estructura:',
	//Para tablas
	messageFormatIdTbl: 'Identificador',
	messageFormatDescTbl: 'Descripcion',
	transformerConfigFileTbl: 'Archivo transformador',
	routerConfigFileTbl: 'Archivo roteador',
	transformerStructTbl: 'Tipo estructura',
	opcionActionTbl: 'Accion'
})
.constant('queueTxt', {
	//Descripcion de las controles individuales
	titulo: 'ADMINISTRADOR DE COLAS',
	//******Descripcion de los atributos
	attribute: 'Atributo general',
	classManager: 'Clase:',
	queueLocation: 'IP-Puerto:',
	sleepTime: 'T. Espera:',
	refreshTime: 'T. Refrescar:',
	//******Descripcion de los Tag
	adminQueueId: 'Identificador:',
	workerThreadsCount: 'N° hilos:',
	messageType: 'TIPO DE MENSAJERIA',
	messageTypeId: 'Id. tipo mensaje:',
	messageTypeDesc: 'Descripcion:',
	supportedMessageFormats: 'Formatos soportados',
	//Tabla de formatos
	messageFormatIdTbl: 'Identificador',
	opcionActionTbl: 'Accion',
	//Descripcion de los titulo de la tabla de colas
	adminQueueIdTbl: 'Identificador',
	workerThreadsCountTbl: 'N° hilos',
	messageTypeIdTbl: 'Formato',
	messageTypeDescTbl: 'Mensaje'
})
.constant('driverTexto', {
	//Descripcion de las controles individuales
	titulo: 'CONFIGURADOR DE DRIVERS',
	driverId: 'Codigo:',
	adminQueueId: 'Cola:',
	type: 'Operacion:',
	timeOutConnect: 'Tiempo conexion:',
	timeOutRead: 'Tiempo lectura:',
	retries: 'N° Reintentos:',
	timeOutQueueRead: 'Tiempo lectura cola:',
	//******Descripcion de los Tag
	name: 'Nombre del driver:',
	port: 'Puerto:',
	maxConcurrentConnections: 'Max. concurrentes:',
	forwardProcess: 'Procesos destino:',
	validaIp: 'Validar IP:',
	concurrentUserSupport: 'Permiso concurrencia:',
	filters: 'FILTROS:',
	inFilters: 'Filtro entradas:',
	outFilters: 'Filtro salidas:',
	//Descripcion de los titulo de la tabla
	driverIdTbl: 'Codigo',
	adminQueueIdTbl: 'Cola',
	typeTbl: 'Operacion:',
	nameTbl: 'Driver',
	portTbl: 'Puerto',
	maxConcurrentConnectionsTbl: 'Conexiones',
	forwardProcessTbl: 'Destino',
	validaIpTbl: 'Validar',
	opcionActionTbl: 'Accion',
	concurrentUserSupportTbl: 'Permiso'
})
.constant('balancerTexto', {
	//Descripcion de las controles individuales
	titulo: 'CONFIGURADOR DE BALANCEADORES',
	attribute: 'Atributo general',
	classManager: 'Clase:',
	queueLocation: 'IP-Puerto:',
	sleepTime: 'T. Espera:',
	refreshTime: 'T. Refrescar:',
	//******Descripcion de los atributos
	id: 'Codigo:',
	//******Descripcion de los Tag
	algorithm: 'Algoritmo:',
	workerThreadsCount: 'N° Hilos:',
	//Tabla de balanceadores
	idTbl: 'Codigo',
	algorithmTbl: 'Algoritmo',
	workerThreadsCountTbl: 'N° Hilos',
	opcionActionTbl: 'Accion'
})
.constant('routeTexto', {
	//Descripcion de las controles individuales
	titulo: 'CONFIGURADOR DE ROUTERS',
	//******Descripcion de los Tag
	routeId: 'Codigo:',
	routeDesc: 'Descripcion:',
	balancerId: 'Balanceador:',
	status: 'Estado:',
	//Tabla de balanceadores
	routeIdTbl: 'Codigo',
	routeDescTbl: 'Descripcion',
	balancerIdTbl: 'Balanceador',
	statustbl: 'Estado',
	opcionActionTbl: 'Accion'
})
.constant('serviceTexto', {
	//Descripcion de las controles individuales
	titulo: 'CONFIGURADOR DE SERVICIOS',
	//******Descripcion de los Tag
	serviceId: 'Identificador:',
	serviceDesc: 'Descripcion:',
	discriminationRules: 'Reglas:',
	authorizationBins: 'Autorizadores:',
	binId: 'Bin Id:',
	binStatus: 'Bin Estado:',
	routes: 'Routes:',
	//Tabla de balanceadores
	serviceIdTbl: 'Codigo',
	serviceDescTbl: 'Descripcion',
	discriminationRulesTbl: 'Reglas',
	authorizationBinsTbl: 'Autorizador',
	opcionActionTbl: 'Accion'
})
.constant('serviceNodeTexto', {
	//Descripcion de las controles individuales
	titulo: 'CONFIGURADOR DE NODOS DE SERVICIOS',
	//******Descripcion de los Tag
	serviceNodeId: 'Identificador:',
	ipAddress: 'IP del nodo:',
	binMonitorPause: 'Bin Monitor:',

	tabAdminChannel: 'Administra-canales',
	tabDefaultChannel: 'Defecto-Canales',
	tabServiceChannel: 'Servicios-Canales',

	adminChannel: 'ADMINISTRADOR DE CANALES',
	defaultChannel: 'CANALES POR DEFECTO',
	serviceChannels: 'Servicio de canales',
	
	adminChannelId: 'Admin-Codigo',
	adminChannelPort: 'Admin-Puerto',
	adminChannelStatus: 'Admin-Estado',

	defaultChannelId: 'Default-Codigo',
	defaultChannelPort: 'Default-Puerto',
	defaultChannelStatus: 'Defeault-Estado',
	workerThreadsCount: 'Cantidad-Hilos',
	useCorrelationID: 'ID-Correlativo',

	serviceChannelId: 'Service-Codigo',
	serviceChannelStatus: 'Service-Status',
	serviceChannelPort: 'Service-Puerto',

	authorizedServices: 'Servicios autorizados:',
	
	connectTimeOut: 'Tiempo-Conexion',
	readTimeOut: 'Tiempo-Lectura',
	timeOutQueueRead: 'Tiempo-Lec-Colas',
	poolSize: 'Tamaño-Pool',
	sleepTime: 'Tiempo-Espera',
	refreshTime: 'Tiempo-Actualizar',
	maxReconnect: 'Maximo-Reconectar',
	reconnectPause: 'Pausa-Reconectar',

	timeoutReceive: 'Timepo-Receptar',
	lagTimeReceive: 'Tiempo-Max-Recept',
	timeoutSend: 'Tiempo-Envio',
	lagTimeSend: 'Tiempo-Max-Envio',
	umbralWarning: 'Advertencia',
	umbralReconnect: 'Reconectar',

	//Tabla de balanceadores
	serviceNodeIdTbl: 'Codigo',
	ipAddressTbl: 'IP',
	binMonitorPauseTbl: 'Monitor',

	adminChannelTbl: 'Administrador',
	defaultChannelTbl: 'Default',
	opcionActionTbl: 'Accion',

	serviceIds: 'Servicios Autorizados',
	serviceChannel: 'Servicios-Canales'
})
.constant('profileTexto', {
	//Descripcion de las controles individuales
	titulo: 'CONFIGURADOR DE PERFILES',
	//******Descripcion de los Tag
	profileId: 'Identificador:',
	profileDesc: 'Descripcion:',
	profileDrivers: 'Perfil Drivers:',
	driverIds: 'Drivers:',
	authServices: 'Autorizador service:',
	serviceIds: 'Services:',
	//Tabla de balanceadores
	profileIdTbl: 'Codigo',
	profileDescTbl: 'Descripcion',
	opcionActionTbl: 'Accion'
})
.constant('clientTexto', {
	//Descripcion de las controles individuales
	titulo: 'CONFIGURADOR DE CLIENTES',
	//******Descripcion de los Tag
	buscarDato: 'Busqueda avanzada',
	sixadcClientId: 'Identificador:',
	sixadcClientDesc: 'Descripcion:',
	atributo: 'Atributos:',
	atrName: 'Nombre atributo:',
	atrValor: 'Valor atributo:',
	ipAddress: 'Ip Address:',
	sixHostname: 'Hostname:',
	sixUsername: 'Username:',
	sixPassword: 'Password:',
	enabled: 'Estado:',
	profileId: 'Perfiles:',
	//Tabla de balanceadores
	sixadcClientIdTbl: 'Codigo',
	sixadcClientDescTbl: 'Descripcion',
	sixHostnameTbl: 'Hostname',
	sixUsernameTbl: 'Username',
	enabledTbl: 'Estado',
	opcionActionTbl: 'Accion'
})
.constant('botonTexto', {
	//Indice de menu general
	formateador: 'Formateador',
	cola: 'Cola',
	driver: 'Driver',
	balanceador: 'Balanceador',
	router: 'Router',
	servicio: 'Servicio',
	nodo: 'Nodo',
	perfil: 'Perfil',
	sixadcClient: 'Cliente',
	salir: 'Salir'
})
.constant('queryRuta', {
	//Atributos generales para todos los tag
	classRoutersManager: 'com.novatronic.sixadc.manager.RoutersManager',
	classDriversManager: 'com.novatronic.sixadc.manager.DriversManager',
	classBalancersManager: 'com.novatronic.sixadc.manager.BalancersManager',
	classChannelManager: 'com.novatronic.sixadc.manager.ChannelManager',
	ipPuerto: 'localhost:1099',
	//Ruta de los servicio expuesto, genenal
	urlControlAcceso: 'http://localhost:8080/RestDAS/ControlAcceso',

	urlMessageFormat: 'http://localhost:8080/RestDAS/MessageFormat',
	urlAdminQueue: 'http://localhost:8080/RestDAS/AdminQueue',
	urlBalancer: 'http://localhost:8080/RestDAS/Balancer',
	urlRoute: 'http://localhost:8080/RestDAS/Route',
	urlDriver: 'http://localhost:8080/RestDAS/Driver',
	urlService: 'http://localhost:8080/RestDAS/Service',
	urlServiceNode: 'http://localhost:8080/RestDAS/ServiceNode',
	urlProfile: 'http://localhost:8080/RestDAS/Profile',
	urlSixadcClient: 'http://localhost:8080/RestDAS/SixadcClient'
})
.constant('mensajeTexto', {
	controlAcceso: 'Control de Acceso',
	controlProceso: 'Control de Proceso',
	sesionExpirada: 'Sesión expirada!!!',
	pregEliminar: '¿Realmente desea eliminar el registro?',
	idDuplicado: 'No se puede guardar por tener identificador duplicado...',
	idPerdido: 'No se puede editar, No existe el identificador...'
});