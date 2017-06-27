### Full Debug log from working application
```
Found KeyTab e:\svc_user.keytab for HTTP/server.dev.local@DEV.LOCAL
Found KeyTab e:\svc_user.keytab for HTTP/server.dev.local@DEV.LOCAL
Entered Krb5Context.acceptSecContext with state=STATE_NEW
Java config name: e:\WORK\kerberos-demo\src\test\resources\kerberos.conf
Loaded from Java config
>>> KeyTabInputStream, readName(): DEV.LOCAL
>>> KeyTabInputStream, readName(): HTTP
>>> KeyTabInputStream, readName(): server.dev.local
>>> KeyTab: load() entry length: 78; type: 23
Looking for keys for: HTTP/server.DEV.local@DEV.LOCAL
Added key: 23version: 0
>>> EType: sun.security.krb5.internal.crypto.ArcFourHmacEType
default etypes for permitted_enctypes: 17 16 18 23.
>>> EType: sun.security.krb5.internal.crypto.ArcFourHmacEType
MemoryCache: add 1498552512/000086/1C644229F69C081101A45CE5C3A78F37/remote_user@DEV.LOCAL to remote_user@DEV.LOCAL|HTTP/server.dev.local@DEV.LOCAL
>>> KrbApReq: authenticate succeed.
Krb5Context setting peerSeqNumber to: 1268827250
>>> EType: sun.security.krb5.internal.crypto.ArcFourHmacEType
Krb5Context setting mySeqNumber to: 133277713
>>> Constrained deleg from GSSCaller{UNKNOWN}
Debug is  true storeKey true useTicketCache true useKeyTab true doNotPrompt true ticketCache is null isInitiator true KeyTab is null refreshKrb5Config is false principal is HTTP/server.dev.local@DEV.LOCAL tryFirstPass is false useFirstPass is false
 storePass is false clearPass is false
Acquire TGT from Cache
>>>KinitOptions cache name is C:\Users\${USER}\krb5cc_${USER}
LSA: Found Ticket
LSA: Made NewWeakGlobalRef
LSA: Found PrincipalName
LSA: Made NewWeakGlobalRef
LSA: Found DerValue
LSA: Made NewWeakGlobalRef
LSA: Found EncryptionKey
LSA: Made NewWeakGlobalRef
LSA: Found TicketFlags
LSA: Made NewWeakGlobalRef
LSA: Found KerberosTime
LSA: Made NewWeakGlobalRef
LSA: Found String
LSA: Made NewWeakGlobalRef
LSA: Found DerValue constructor
LSA: Found Ticket constructor
LSA: Found PrincipalName constructor
LSA: Found EncryptionKey constructor
LSA: Found TicketFlags constructor
LSA: Found KerberosTime constructor
LSA: Finished OnLoad processing
>> Acquire default native Credentials
default etypes for default_tkt_enctypes: 17 16 18 23.
LSA: Found KrbCreds constructor
LSA: Got handle to Kerberos package
LSA: Response size is 1653
LSA: TICKET SessionKey KeyType is 18
LSA: Valid etype found: 18
LSA: Principal domain is DEV.LOCAL
LSA: Name type is 1
LSA: Name count is 1
LSA: Principal domain is DEV.LOCAL
LSA: Name type is 2
LSA: Name count is 2
LSA: Session key all zero. Stop.
>>> Found no TGT's in LSA
Principal is HTTP/server.dev.local@DEV.LOCAL
null credentials from Ticket Cache
Looking for keys for: HTTP/server.dev.local@DEV.LOCAL
Added key: 23version: 0
>>> KdcAccessibility: reset
Looking for keys for: HTTP/server.dev.local@DEV.LOCAL
Added key: 23version: 0
default etypes for default_tkt_enctypes: 17 16 18 23.
>>> KrbAsReq creating message
getKDCFromDNS using UDP
>>> KrbKdcReq send: kdc=adserver.dev.local. UDP:88, timeout=30000, number of retries =3, #bytes=163
>>> KDCCommunication: kdc=adserver.dev.local. UDP:88, timeout=30000,Attempt =1, #bytes=163
>>> KrbKdcReq send: #bytes read=183
>>>Pre-Authentication Data:
         PA-DATA type = 11
         PA-ETYPE-INFO etype = 23, salt =

>>>Pre-Authentication Data:
         PA-DATA type = 19
         PA-ETYPE-INFO2 etype = 23, salt = null, s2kparams = null

>>>Pre-Authentication Data:
         PA-DATA type = 2
         PA-ENC-TIMESTAMP
>>>Pre-Authentication Data:
         PA-DATA type = 16

>>>Pre-Authentication Data:
         PA-DATA type = 15

>>> KdcAccessibility: remove adserver.dev.local.:88
>>> KDCRep: init() encoding tag is 126 req type is 11
>>>KRBError:
         sTime is Tue Jun 27 10:35:12 CEST 2017 1498552512000
         suSec is 82330
         error code is 25
         error Message is Additional pre-authentication required
         sname is krbtgt/DEV.LOCAL@DEV.LOCAL
         eData provided.
         msgType is 30
>>>Pre-Authentication Data:
         PA-DATA type = 11
         PA-ETYPE-INFO etype = 23, salt =

>>>Pre-Authentication Data:
         PA-DATA type = 19
         PA-ETYPE-INFO2 etype = 23, salt = null, s2kparams = null

>>>Pre-Authentication Data:
         PA-DATA type = 2
         PA-ENC-TIMESTAMP
>>>Pre-Authentication Data:
         PA-DATA type = 16

>>>Pre-Authentication Data:
         PA-DATA type = 15

KrbAsReqBuilder: PREAUTH FAILED/REQ, re-send AS-REQ
default etypes for default_tkt_enctypes: 17 16 18 23.
Looking for keys for: HTTP/server.dev.local@DEV.LOCAL
Added key: 23version: 0
Looking for keys for: HTTP/server.dev.local@DEV.LOCAL
Added key: 23version: 0
default etypes for default_tkt_enctypes: 17 16 18 23.
>>> EType: sun.security.krb5.internal.crypto.ArcFourHmacEType
>>> KrbAsReq creating message
getKDCFromDNS using UDP
>>> KrbKdcReq send: kdc=adserver.dev.local. UDP:88, timeout=30000, number of retries =3, #bytes=246
>>> KDCCommunication: kdc=adserver.dev.local. UDP:88, timeout=30000,Attempt =1, #bytes=246
>>> KrbKdcReq send: #bytes read=98
>>> KrbKdcReq send: kdc=adserver.dev.local. TCP:88, timeout=30000, number of retries =3, #bytes=246
>>> KDCCommunication: kdc=adserver.dev.local. TCP:88, timeout=30000,Attempt =1, #bytes=246
>>>DEBUG: TCPClient reading 1510 bytes
>>> KrbKdcReq send: #bytes read=1510
>>> KdcAccessibility: remove adserver.dev.local.:88
Looking for keys for: HTTP/adserver.dev.local@DEV.LOCAL
Added key: 23version: 0
>>> EType: sun.security.krb5.internal.crypto.ArcFourHmacEType
>>> KrbAsRep cons in KrbAsReq.getReply HTTP/server.dev.local
principal is HTTP/server.dev.local@DEV.LOCAL
Will use keytab
Commit Succeeded

Found ticket for HTTP/server.dev.local@DEV.LOCAL to go to krbtgt/DEV.LOCAL@DEV.LOCAL expiring on Tue Jun 27 20:35:12 CEST 2017
Entered Krb5Context.initSecContext with state=STATE_NEW
Found ticket for HTTP/server.dev.local@DEV.LOCAL to go to krbtgt/DEV.LOCAL@DEV.LOCAL expiring on Tue Jun 27 20:35:12 CEST 2017
Service ticket not found in the subject
>>> Credentials acquireServiceCreds: same realm
default etypes for default_tgs_enctypes: 17 16 18 23.
>>> CksumType: sun.security.krb5.internal.crypto.RsaMd5CksumType
>>> EType: sun.security.krb5.internal.crypto.ArcFourHmacEType
getKDCFromDNS using UDP
>>> KrbKdcReq send: kdc=pidads02.DEV.local. TCP:88, timeout=30000, number of retries =3, #bytes=1526
>>> KDCCommunication: kdc=pidads02.DEV.local. TCP:88, timeout=30000,Attempt =1, #bytes=1526
>>>DEBUG: TCPClient reading 1500 bytes
>>> KrbKdcReq send: #bytes read=1500
>>> KdcAccessibility: remove pidads02.DEV.local.:88
>>> EType: sun.security.krb5.internal.crypto.ArcFourHmacEType
>>> KrbApReq: APOptions are 00000000 00000000 00000000 00000000
>>> EType: sun.security.krb5.internal.crypto.Aes128CtsHmacSha1EType
Krb5Context setting mySeqNumber to: 477594230
Krb5Context setting peerSeqNumber to: 0
Created InitSecContextToken:
0000: 01 00 6E 82 05 8F 30 82   05 8B A0 03 02 01 05 A1  ..n...0.........
0010: 03 02 01 0E A2 07 03 05   00 00 00 00 00 A3 82 04  ................
0020: 98 61 82 04 94 30 82 04   90 A0 03 02 01 05 A1 0F  .a...0..........
<snip>
0590: CB 82 68 A5 3B                                     ..h.;

Krb5Context.unwrap: token=[05 04 01 ff 00 0c 00 0c 00 00 00 00 1c 77 82 76 98 4d 33 62 5f ee 82 19 cf 32 59 2e 07 a0 00 00 ]
Krb5Context.unwrap: data=[07 a0 00 00 ]
Krb5Context.wrap: data=[01 01 00 00 ]
Krb5Context.wrap: token=[05 04 00 ff 00 0c 00 00 00 00 00 00 1c 77 82 76 01 01 00 00 d4 9c 02 56 9e f7 60 1f 8e e8 ba 35 ]
```
