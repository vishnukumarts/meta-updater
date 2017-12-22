SUMMARY = "Aktualizr configuration for implicit provisioning"
DESCRIPTION = "Systemd service and configurations for implicitly provisioning Aktualizr, the SOTA Client application written in C++"
HOMEPAGE = "https://github.com/advancedtelematic/aktualizr"
SECTION = "base"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=9741c346eef56131163e13b9db1241b3"
DEPENDS = "aktualizr-native"
RDEPENDS_${PN} = "aktualizr"
PV = "1.0"
PR = "1"

SRC_URI = " \
  file://LICENSE \
  "


require environment.inc
require credentials.inc

do_install() {
    install -d ${D}${libdir}/sota
    if [ -n "${SOTA_PACKED_CREDENTIALS}" ]; then
        aktualizr_implicit_writer -c ${SOTA_PACKED_CREDENTIALS} \
            -i ${STAGING_DIR_NATIVE}${libdir}/sota/sota_implicit_prov.toml -o ${D}${libdir}/sota/sota.toml -p ${D} --no-root-ca
    fi
}

FILES_${PN} = " \
                ${libdir}/sota/sota.toml \
                ${libdir}/sota/root.crt \
                "

# vim:set ts=4 sw=4 sts=4 expandtab:
